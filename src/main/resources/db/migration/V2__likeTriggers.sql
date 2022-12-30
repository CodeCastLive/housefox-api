-- INCREASE & DECREASE LIKE
-- AFTER CREATE
CREATE FUNCTION increase_like_function()
RETURNS TRIGGER AS
$$
BEGIN
UPDATE house SET like_count = like_count+1 WHERE id = NEW.house_id and NEW.type = 'LIKE';
RETURN NEW;
END;
$$

LANGUAGE 'plpgsql';

CREATE TRIGGER increase_like
    AFTER INSERT ON house_like
    FOR EACH ROW
    EXECUTE PROCEDURE increase_like_function();

-- AFTER UPDATE
CREATE FUNCTION like_update_function()
RETURNS TRIGGER AS
$$
BEGIN
UPDATE house SET like_count = like_count+1 WHERE id = NEW.house_id and NEW.type = 'LIKE';
UPDATE house SET like_count = like_count-1 WHERE id = NEW.house_id and NEW.type = 'DISLIKE';
RETURN NEW;
END;
$$

LANGUAGE 'plpgsql';


CREATE TRIGGER update_like
    AFTER UPDATE ON house_like
    FOR EACH ROW
    EXECUTE PROCEDURE like_update_function();


-- AFTER DELETE
CREATE FUNCTION like_delete_function()
RETURNS TRIGGER AS
$$
BEGIN
UPDATE house SET like_count = like_count-1 WHERE id = OLD.house_id and OLD.type = 'LIKE';
RETURN OLD;
END;
$$

LANGUAGE 'plpgsql';


CREATE TRIGGER delete_like
    AFTER DELETE ON house_like
    FOR EACH ROW
    EXECUTE PROCEDURE like_delete_function();

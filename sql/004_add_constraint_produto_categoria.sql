ALTER TABLE produto
ADD CONSTRAINT fk_categoria_id
FOREIGN KEY (fk_categoria_id)
REFERENCES categoria(id)
ON DELETE CASCADE;
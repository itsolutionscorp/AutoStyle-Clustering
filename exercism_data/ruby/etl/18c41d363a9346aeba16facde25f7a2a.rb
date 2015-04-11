class ETL
  def self.transform(old_hash)
    old_hash = old_hash.invert.keys.flatten.join.downcase
    new_hash = {}
    new_hash.store("a", 1) if old_hash.include?("a")
    new_hash.store("b", 3) if old_hash.include?("b")
    new_hash.store("c", 3) if old_hash.include?("c")
    new_hash.store("d", 2) if old_hash.include?("d")
    new_hash.store("e", 1) if old_hash.include?("e")
    new_hash.store("f", 4) if old_hash.include?("f")
    new_hash.store("g", 2) if old_hash.include?("g")
    new_hash.store("h", 4) if old_hash.include?("h")
    new_hash.store("i", 1) if old_hash.include?("i")
    new_hash.store("j", 8) if old_hash.include?("j")
    new_hash.store("k", 5) if old_hash.include?("k")
    new_hash.store("l", 1) if old_hash.include?("l")
    new_hash.store("m", 3) if old_hash.include?("m")
    new_hash.store("n", 1) if old_hash.include?("n")
    new_hash.store("o", 1) if old_hash.include?("o")
    new_hash.store("p", 3) if old_hash.include?("p")
    new_hash.store("q", 10) if old_hash.include?("q")
    new_hash.store("r", 1) if old_hash.include?("r")
    new_hash.store("s", 1) if old_hash.include?("s")
    new_hash.store("t", 1) if old_hash.include?("t")
    new_hash.store("u", 1) if old_hash.include?("u")
    new_hash.store("v", 4) if old_hash.include?("v")
    new_hash.store("w", 4) if old_hash.include?("w")
    new_hash.store("x", 8) if old_hash.include?("x")
    new_hash.store("y", 4) if old_hash.include?("y")
    new_hash.store("z", 10) if old_hash.include?("z")
  new_hash
  end
end

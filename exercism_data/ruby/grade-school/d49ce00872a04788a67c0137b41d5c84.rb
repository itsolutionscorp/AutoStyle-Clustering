class School

  attr_reader :db

  def initialize
    @db = Hash.new([]) 
  end

  def add(name, grade)
    if db.has_key?(grade)
      db[grade] << name
    else
      db[grade] = [name]
    end
    db 
  end

  def grade(grade)
    db[grade]
  end

  def sort
    db.keys.sort.each_with_object(Hash.new) do |key, hash|
      hash[key] = db[key].sort
    end
  end

private

  attr_writer :db
end

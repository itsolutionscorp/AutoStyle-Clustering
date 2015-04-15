class School

  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    db[grade] += [name]
  end

  def grade(grade)
    db[grade].sort
  end

  def sort
    db.keys.sort.each_with_object(Hash.new) do |key, hash|
      hash[key] = db[key].sort
    end
  end
  
  def to_hash
    sort
  end

  private
  attr_writer :db
end

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
  
  def to_hash
    db.keys.sort.each_with_object({}) do |key, hash|
      hash[key] = db[key].sort
    end
  end

  private
  attr_writer :db
end

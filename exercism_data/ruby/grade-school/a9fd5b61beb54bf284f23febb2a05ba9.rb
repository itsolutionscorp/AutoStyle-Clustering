class School
  attr_reader :db

  def db
    @db ||= Hash.new {|hash, key| hash[key] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort
    @db = db.keys.sort.each_with_object({}) do |key, sorted_db|
      sorted_db[key] = db[key].sort 
    end
  end

end

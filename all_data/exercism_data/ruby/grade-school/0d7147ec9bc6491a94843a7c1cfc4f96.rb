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
    Hash[sort_names.sort]
  end

  private 

  def sort_names
    @db.each_value { |v| v.sort! }
  end
end

class School
  def initialize()
    @db = Hash.new {|hsh, key| hsh[key] = [] }
  end

  def db
    @db
  end

  def grade(grade)
    return @db[grade]
  end

  def sort
    sort = sort_grades()
    sort_students(sort)
  end

  def sort_grades()
    sort = {}
    @db.keys.sort.each { |key| sort[key] = @db[key] }
    return sort
  end

  def sort_students(hash)
    hash.each { |key, value| hash[key] = value.sort }
  end

  def add(name, grade)
    @db[grade] << name
  end
end

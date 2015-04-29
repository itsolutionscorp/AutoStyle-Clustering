class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name,grade)
    grade_has_records(grade) ? add_to_record(name,grade) : create_record(name,grade)
  end

  def grade_has_records(grade)
    @db[grade]
  end

  def add_to_record(name,grade)
    @db[grade] << name
  end

  def create_record(name,grade)
    @db[grade] = [name]
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort
    @db.sort.map{ |grade,names| {grade => names.sort} }.reduce({},:merge)
  end
end

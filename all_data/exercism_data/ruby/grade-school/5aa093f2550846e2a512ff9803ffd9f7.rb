class School
  attr_accessor :db

  def initialize name
    @name = name
    @db = {}
  end

  def add name, grade
    @db[grade] = [] unless @db[grade]
    @db[grade] << name
  end

  def grade grade
    return @db[grade] if @db[grade]
    []
  end

  def sort
    @db.keys.sort.each_with_object({}) { |k,hash| hash[k] = sort_students @db[k] }
  end

  private

  def sort_students students
    students.sort
  end

end

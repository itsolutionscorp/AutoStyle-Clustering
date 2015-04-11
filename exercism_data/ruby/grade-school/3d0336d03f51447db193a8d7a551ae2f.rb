class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    @db[grade] = add_student(grade, name)
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.sort.each_with_object({}) do |class_student, temp|
      temp[class_student[0]] = class_student[1].sort
    end
  end

  private

  def add_student(grade, name)
    @db.has_key?(grade) ? @db[grade].push(name) : [name]
  end
end

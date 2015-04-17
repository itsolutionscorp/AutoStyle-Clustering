class School
  attr_reader :db

  def initialize(school)
    @db = Hash.new{ |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    db[grade] ||= []

    db[grade] << student
  end

  def grade(number)
    db[number]
  end

  def sort
    students = {}

    db.keys.sort.each do |key|
      students[key] = db[key].sort
    end

    students
  end

  private

  def create_grade(grade)
    db[grade] ||= []
  end
end
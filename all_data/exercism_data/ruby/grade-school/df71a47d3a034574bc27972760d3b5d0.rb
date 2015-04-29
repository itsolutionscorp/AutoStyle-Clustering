class School
  attr_reader :db

  def initialize(school)
    @db = {}
  end

  def add(student, grade)
    create_grade(grade)

    db[grade] += [student]
  end

  def grade(number)
    create_grade(number)

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
    db[grade] = [] if db[grade].nil?
  end
end

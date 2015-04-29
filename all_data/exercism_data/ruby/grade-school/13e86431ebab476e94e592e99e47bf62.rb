class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grade_number)
    roster = grade(grade_number) + [student]

    @db.update(grade_number => roster)
  end

  def grade(grade_number)
    @db[grade_number] || []
  end

  def sort
    grade_numbers.reduce({}) do |sorted, grade_number|
      sorted.update(grade_number => grade(grade_number).sort)
    end
  end

  def grade_numbers
    @db.keys.sort
  end

end

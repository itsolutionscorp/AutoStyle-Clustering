class School
  attr_reader :school

  def initialize
    @school = Array.new
  end

  def to_hash
    @school = Hash[ school.group_by(&:first)
                          .map {|k,a| [k, a.map(&:last).sort] }
                          .sort
                  ]
  end

  def add(name, grade)
    school << [grade, name]
  end

  def grade(grade_number)
    grade = self.to_hash
    if grade[grade_number] == nil
      []
    else
      students_in_grade = grade[grade_number]
    end
  end
end

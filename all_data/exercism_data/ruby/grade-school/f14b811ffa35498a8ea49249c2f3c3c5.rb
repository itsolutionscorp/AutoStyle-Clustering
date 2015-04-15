class School
  def initialize
    @grades = {}
  end

  def add(name, grade)
    # if @grades[grade] was nil, this is equivalent to just [name]
    @grades[grade] = [name, *@grades[grade]]
  end

  def grade(grade)
    (@grades[grade] || []).sort
  end

  def grades
    @grades.keys.sort
  end

  def to_hash
    grades.each_with_object({}) do |grade, hash|
      hash[grade] = self.grade(grade)
    end
  end
end

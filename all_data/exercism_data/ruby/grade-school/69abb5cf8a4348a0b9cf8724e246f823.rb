class School

  def initialize
    @grades = Hash.new
  end

  def to_hash
    @grades.sort.inject({}) { |hash, (grade, list)| hash[grade] = list.sort; hash }
  end

  def add name, grade
    (@grades[grade] ||= []) << name
  end

  def grade num
    (students = @grades[num]) and students.sort or []
  end

end

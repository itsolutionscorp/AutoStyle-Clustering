class School
  def initialize
    @school = {}
  end

  def add(student, grade_num)
    unless @school.key?(grade_num)
      @school[grade_num] = []
      @school = @school.sort_by { |grade, _| grade }.to_h
    end
    @school[grade_num] = @school[grade_num].push(student).sort
  end

  def grade(grade_num)
    @school.key?(grade_num) ?  @school[grade_num] : []
  end

  def to_hash
    @school
  end
end

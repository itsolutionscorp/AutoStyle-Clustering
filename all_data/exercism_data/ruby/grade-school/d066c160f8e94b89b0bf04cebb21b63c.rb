class School

  def initialize
    @school = {}
  end

  def db
    @school
  end

  def grade grade
    if @school[grade] == nil
      []
    else
      @school[grade]
    end
  end

  def sort
    @school.values.each do |value|
      value.sort!
    end
    Hash[@school.sort]
  end

  def add student_name, grade
    if @school[grade].class == Array
      @school[grade] = @school[grade].push student_name
    else
      @school[grade] = [student_name]
    end
  end
end

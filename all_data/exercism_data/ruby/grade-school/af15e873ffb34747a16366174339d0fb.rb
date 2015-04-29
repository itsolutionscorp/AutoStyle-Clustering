class School
  @@school_info
  def initialize()
  	@@school_info = {}
  end

  def add(student, grade)
  	@@school_info[grade] == nil ? (@@school_info[grade] = [student]) : (@@school_info[grade] << student)
  end

  def to_hash()
  	@@school_info.each { |grade, student| student.sort! }
    Hash[@@school_info.sort_by{|grade, student| grade}]
  end

  def grade(grade)
    @@school_info[grade] == nil ? [] : @@school_info[grade].sort
  end
end

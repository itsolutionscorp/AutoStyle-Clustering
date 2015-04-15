class School
  def initialize
    # NOTE: setting to a default array is possible but ugly
    @class_roster = Hash.new()
  end

  def add(student, grade_level)
    @class_roster[grade_level] ||= []
    @class_roster[grade_level] << student
  end

  def db
    @class_roster
  end

  def grade(grade_level)
    @class_roster[grade_level] || []
  end

  def sort
    # NOTE: this is naive as K would need to come first and is a different type
    grades = @class_roster.keys.sort
    grades.each_with_object(Hash.new()) do |grade_level, sorted_roster|
      sorted_roster[grade_level] = grade(grade_level).sort
    end
  end
end

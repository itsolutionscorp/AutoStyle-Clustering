class School
  attr_reader :school_name
  attr_accessor :db

  def initialize(school_name)
    @school_name = school_name
    @db = Hash.new
  end

  def add(student_name, grade_value)
    initialize_grade(grade_value)
    db[grade_value] << student_name
  end

  def grade(grade_value)
    initialize_grade(grade_value)
    db[grade_value]
  end

  def sort
    db_sorted = Hash.new
    Hash[db.sort].each do |k, v|
      db_sorted[k] = v.sort
    end
    db_sorted
  end

  private

  def initialize_grade(grade_value)
    db[grade_value] = [] if db[grade_value] == nil
  end  
end  

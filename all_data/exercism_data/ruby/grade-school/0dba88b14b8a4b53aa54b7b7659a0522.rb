class School
  
  attr_reader :db
  
  def initialize
    @db = {}
  end
  
  def add(name, grade)
    grade(grade) << name
  end
  
  def grade(grade)
    @db[grade] || (@db[grade] = [])
  end
  
  def sort
    dbclone = {}
    @db.keys.sort.inject({}) do |dbclone, grade|
      dbclone[grade] = grade_sorted_by_name(grade)
      dbclone
    end
  end
  
  private
  
  def grade_sorted_by_name(grade)
    grade(grade).sort
  end
  
end

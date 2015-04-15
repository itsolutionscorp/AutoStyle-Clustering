class School
  def initialize()
    @grades_names = {}
  end
  
  def to_hash()
    @grades_names.to_a.sort_by {|grade,name| grade}.to_h
  end
  
  def add(student, grade)
    if @grades_names[grade] == nil
      @grades_names[grade] = [student]
    else
      @grades_names[grade] += [student]
      @grades_names[grade].sort!
    end
  end
  
  def grade(gradenum)
    if @grades_names[gradenum] == nil
      return []
    else
      @grades_names[gradenum]
    end
  end
end

class School

  def initialize
    @student_registry ||= Hash.new(0)
  end 

  def to_hash
    @student_registry.sort_by { |k,v| [k, v.sort!] }
    @student_registry = Hash[@student_registry.sort_by { |k, v| k} ] 
    @student_registry
  end

  def add(student, grade)
    @student_registry[grade] = [] if  @student_registry[grade] == 0  
    @student_registry[grade] << student
  end

  def grade(g)
    if @student_registry[g] == 0
      []
    else
      @student_registry[g].sort
    end
  end
end

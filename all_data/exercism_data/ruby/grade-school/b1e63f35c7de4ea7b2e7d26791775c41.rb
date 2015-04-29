class School
  def initialize
    @arc = {}
  end

  def to_hash
    sorted_arc = {}
    @arc.sort.each do |grade, students|
      sorted_arc[grade] = students.sort
    end
    sorted_arc
  end

  def add name, grade
    (@arc[grade] ||= []) << name   
  end

  def grade num
    (@arc[num] || []).sort
  end
end

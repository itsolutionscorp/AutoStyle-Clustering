class School
  def initialize
    @school = {}
  end

  def to_hash
    @school.sort.to_h
  end

  def add student, grade
    if @school[grade].nil?
      @school[grade] = [student]
    else
      @school[grade] << student
      @school[grade].sort!
    end
  end

  def grade n
    @school[n].nil? ? [] : @school[n]
  end
end

class School

  def initialize
    @school = Hash.new []
  end

  def to_hash
    @school.sort.each_with_object({}) do |grade_info, hash|
      hash[grade_info[0]] = grade_info[1].sort
    end
  end

  def add name, grade
    if @school[grade].empty?
      @school[grade] = [name]
    else
      @school[grade] << name
    end
  end

  def grade grade
    @school[grade].sort
  end
end

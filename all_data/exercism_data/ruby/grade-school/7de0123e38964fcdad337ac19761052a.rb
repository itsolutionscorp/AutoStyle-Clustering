class School
  def initialize
    @school = Hash.new { |h,k| h[k] = [] }
  end

  def add(name, grade)
    @school[grade] << name
    @school[grade].sort!
  end

  def grade(grade)
    @school[grade]
  end

  def to_hash
    hash = {}
    @school.keys.sort.each do |k|
      hash[k] = @school[k]
    end
    hash
  end
end

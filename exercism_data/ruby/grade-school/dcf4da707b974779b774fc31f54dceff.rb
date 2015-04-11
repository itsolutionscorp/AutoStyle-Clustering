class School
  def initialize
    @school_hash = Hash.new {|h, k| h[k] = []}
  end
  
  def add(student, grade)
    @school_hash[grade] << student
  end

  def to_hash
    Hash[@school_hash.sort_by {|k, v| k}]
  end

  def grade(number)
    @school_hash[number].sort
  end
end

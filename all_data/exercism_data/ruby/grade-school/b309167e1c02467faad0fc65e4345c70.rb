class School
  def initialize
    @school = {}
  end

  def add(name, grade)
    @school[grade] ||= []
    @school[grade] << name

  end

  def grade(grade)
    if @school[grade].nil?
      []
    else
      @school[grade].sort
    end
  end

  def to_hash
    temp_hash = @school
    temp_hash.to_h.each do |k,v|
      temp_hash[k] = v.sort
    end
    temp_hash.sort.to_h
  end

end

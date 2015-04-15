class School

  def initialize
    @students_hash = {}
  end

  def to_hash
    sorted_values = @students_hash.each do |grade, name|
      @students_hash[grade] = @students_hash[grade].sort
    end
    Hash[sorted_values.sort]
  end

  def add(name, grade)
    if @students_hash[grade] == nil
      @students_hash[grade] = [name]
    else
      @students_hash[grade] += [name]
    end
  end

  def grade(grade)
    @students_hash[grade] ||= []
    @students_hash[grade] = @students_hash[grade].sort
  end

end

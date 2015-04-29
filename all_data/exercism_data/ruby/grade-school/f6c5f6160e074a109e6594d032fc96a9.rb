class School
  def initialize
    @students = {}
  end

  def to_hash
    hash = {}
    @students.keys.sort.each do |grade|
      hash[grade] = @students[grade]
    end
    hash

    # @students.keys.sort.inject({}) { |hash, grade| hash[grade] = @students[grade].sort }
  end

  def add(name, grade)
    if @students[grade]
      @students[grade] << name
      @students[grade].sort!
    else
      @students[grade] = [name]
    end
  end

  def grade(g)
    @students[g] ? @students[g] : []
  end


end

class School
  attr_reader :to_hash
  def initialize
    @to_hash = Hash.new { [] }
  end

  def grade(number)
    @to_hash[number]
  end

  def add(name, number)
    if @to_hash[number]
      @to_hash[number] += [name]
    else
      @to_hash[number] = [name]
    end
    @to_hash = Hash[@to_hash.sort]
    @to_hash[number].sort!
  end

end

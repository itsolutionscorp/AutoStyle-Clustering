class School

  def initialize
    @s_hash = {}
  end

  def to_hash
    Hash[@s_hash.sort]
  end

  def add(name, grade)
    return @s_hash.merge!({grade => [name]}) unless @s_hash.has_key? grade
    @s_hash[grade].push(name).sort!
  end

  def grade(num)
    return [] unless @s_hash.has_key? num
    @s_hash[num]
  end
end

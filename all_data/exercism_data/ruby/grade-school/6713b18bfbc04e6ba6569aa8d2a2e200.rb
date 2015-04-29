class School
  def initialize
    @ledger = Hash.new {|hsh, key| hsh[key] = []}
  end

  def to_hash
    Hash[@ledger.sort_by{|k,v| k}]
  end

  def add (name, grade)
    @ledger[grade] << name
    @ledger[grade].sort!
  end

  def grade (num)
    @ledger[num]
  end

end

class School
  def add(name, grade)
    store[grade] << name
    store[grade].sort!

    # Protect against modification
    nil
  end

  def grade(grade)
    # #dup to protect against modification
    store[grade].dup
  end

  def to_hash
    # New hash to protect against modification
    Hash[store.sort]
  end


private
  def store
    @store ||= Hash.new { |h,k| h[k] = [] }
  end
end

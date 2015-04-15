class School < Hash

  def add(name, grade)
    if self.has_key?(grade)
      self[grade].push(name)
      self[grade].sort!
    else
      self[grade] = [name]
    end
     p self.sort.to_h
  end

  def grade(num)
    ans = []
    self.each do |key, value|
      if key == num
        self[key].each do |name|
          ans << name
        end
      end
    end
    ans.sort
  end
end

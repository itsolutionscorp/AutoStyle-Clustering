class School

 def initialize
    @archive = Hash.new
  end

  def add(name, grade)
    if @archive.has_key?(grade)
      @archive[grade].push(name)
      @archive[grade].sort!
    else
      @archive[grade] = [name]
    end
  end

  def grade(num)
    ans = []
    @archive.each do |key, value|
      if key == num
        @archive[key].each do |name|
          ans << name
        end
      end
    end
    ans.sort
  end

  def to_hash
    Hash[@archive.sort]
  end

  def keys
    @archive.sort.to_h.map{ |x, y| x }
  end

end

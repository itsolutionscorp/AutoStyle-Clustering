class Robot
  def initialize
    prefix_shuffle
    suffix_shuffle
  end

  def prefix_shuffle
    @pre = ("A".."Z").to_a.shuffle!
  end

  def suffix_shuffle
    @suf = (100..999).to_a.shuffle!
  end

  def name
    @suffix = @suf.pop.to_s
    @prefix = @pre.pop(2).join
    @joined ||= "#{@prefix}#{@suffix}"
  end

  def reset 
    @joined = nil
  end
end

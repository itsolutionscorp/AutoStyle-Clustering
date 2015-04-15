class Year
  def self.leap? year
    year.%(4).zero? and year.%(100).nonzero? or year.%(400).zero?
  end
end

class Year
  def Year.leap? (n)
    (n % 4).zero? and not (n % 100).zero? or (n % 400).zero?
  end
end

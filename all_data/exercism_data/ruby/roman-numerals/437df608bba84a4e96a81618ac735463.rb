class Integer
  def to_roman(result = '')
    map_roman.invert.keys.each do |d|
      q,m = self.divmod(d)
      result << map_roman.key(d) * q
      next if q == 0
      break if m == 0
      return m.to_roman(result)
    end
    result

  end

  private

  def map_roman
    {
      'M' => 1000,
      'CM' => 900,
      'D' => 500,
      'CD' => 400,
      'C' => 100,
      'XC' => 90,
      'L' => 50,
      'XL' => 40,
      'X' => 10,
      'IX' => 9,
      'V' => 5,
      'IV' => 4,
      'I' => 1
    }
  end
end

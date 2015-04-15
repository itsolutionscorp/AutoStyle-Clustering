class Fixnum
  def to_roman
    i = self
    res = ""

    digit = ->(name, value) do
      while i >= value
        res << name
        i -= value
      end
    end

    digit['M', 1000]
    digit['D', 500]
    digit['C', 100]
    digit['L', 50]
    digit['X', 10]
    digit['V', 5]
    digit['I', 1]

    res
      .gsub('DCCCC', 'CM')
      .gsub('CCCC', 'CD')
      .gsub('LXXXX', 'XC')
      .gsub('XXXX', 'XL')
      .gsub('VIIII', 'IX')
      .gsub('IIII', 'IV')
  end
end

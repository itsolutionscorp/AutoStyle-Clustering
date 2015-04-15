class Grains
  def square(field)
    2**(field-1)
  end

  def total
    (1..64).inject { |total, field| total +  square(field)}
  end
end

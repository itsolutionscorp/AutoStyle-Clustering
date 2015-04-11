class Object
  def blank?; respond_to?(:empty?) ? !!empty? : !self end
  def present?; !blank? end
  def presence; self if present? end
end

class Fixnum

  def to_roman(n=self)
    {
      1000 => 'M' ,
      900  => 'CM',
      500  => 'D' ,
      400  => 'CD',
      100  => 'C' ,
      90   => 'XC',
      50   => 'L' ,
      40   => 'XL',
      10   => 'X' ,
      9    => 'IX',
      5    => 'V' ,
      4    => 'IV'
    }.each_with_object([]) do |(k,v), acc|
      (acc << v + to_roman(n-k)) and break acc if n >= k
    end.join.presence || ('I' * n)
  end

end

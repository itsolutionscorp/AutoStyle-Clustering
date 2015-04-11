module Roman
  class RomanNumbers
    private
    class << self
      def values
        { 1000=>'M', 900=>'CM', 500=>'D', 400=>'CD', 100=>'C', 90=>'XC', 50=>'L', 40=>'XL', 10=>'X', 9=>'IX', 5=>'V', 4=>'IV', 1=>'I' }
      end

      def keys
        values.keys.sort.reverse
        #Ruby 2 keeps hashes sorted, but if using < 2, you need to sort the keys in descending order
      end
    end
  end

  public
  def to_roman
    values=''
    temp_number=self
    RomanNumbers.keys.each do |key|
      while temp_number >= key
        values << RomanNumbers.values[key]
        temp_number-=key
      end
    end
    values
  end
end

::Fixnum.include(Roman)

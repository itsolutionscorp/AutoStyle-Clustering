# Thoughts on implementation of conversion:  Need to find largest component,
# then work out whether or not it is higher than previous 'half' level, or one
# or less units to the next 'full' level.

module Romans
  class RomanComponents
    @Thousands = 0
    @Hundreds  = 0
    @Tens      = 0 
    @Ones      = 0

    @@CANONICAL_DATA = {
      :ONES      => ['I','V'],
      :TENS      => ['X','L'],
      :HUNDREDS  => ['C','D'],
      :THOUSANDS => 'M'
    }

    def initialize(input)
      @Thousands, @Hundreds, @Tens, @Ones = Romans.get_digits(input)
    end

    def gen_canonical_componentf(sym, numval)
      if numval == 0
        return Proc.new { "" }
      end

      case sym
      when :THOUSANDS
        Proc.new do
          @@CANONICAL_DATA[sym] * numval
        end
      when :HUNDREDS,:TENS,:ONES
        Proc.new do
          case numval
          when 1..3 # I, II, III
            @@CANONICAL_DATA[sym][0] * numval
          when 4    # IV
            @@CANONICAL_DATA[sym][0] + @@CANONICAL_DATA[sym][1]
          when 5    # V
            @@CANONICAL_DATA[sym][1]
          when 6..8 # VI, VII, VIII
            @@CANONICAL_DATA[sym][1] + (@@CANONICAL_DATA[sym][0] * (numval-5) )
          when 9    # IX
            # This feels so bad coming from Perl (where Hash key order is not
            # preserved), and should probably be reworked by adding the next
            # symbol at the end of the value array, but I'm not sure if that
            # would be any less janky.
            @@CANONICAL_DATA[sym][0] + 
              @@CANONICAL_DATA[@@CANONICAL_DATA.keys[
                @@CANONICAL_DATA.keys.find_index(sym)+1]][0]
          end
        end
      end
    end

    def to_canonical_form
      canonical_form = ""
      zipped_sym_val = @@CANONICAL_DATA
        .keys
        .zip([@Ones,@Tens,@Hundreds,@Thousands])
        .reverse
      zipped_sym_val.each do |argpiece|
        canonical_form << gen_canonical_componentf(argpiece[0],argpiece[1]).call
      end
      canonical_form
    end

  end
  # Returns array of thousands, hundreds, tens, and ones digits
  # Funny story: This replaced a 20-line method filled with a fat case
  # statement before I realized how this could work.
  def self.get_digits(num)
    temp = num.to_s.chars.map! { |i| i.to_i}

    (4-(temp.length)).times do
      temp.unshift(0)
    end

    temp
  end

end

class Integer
  # Test looks like it's using built-in numbers, so we have to override.
  # I don't think it makes sense to have this defined for 'Numeric', so we'll
  # keep it just for Integer.
  def to_roman
    r = Romans::RomanComponents.new(self)
    r.to_canonical_form
  end
end

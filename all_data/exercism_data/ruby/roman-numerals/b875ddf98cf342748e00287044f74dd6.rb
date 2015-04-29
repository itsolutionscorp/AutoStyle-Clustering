class RomanBase < Struct.new(:base, :one, :five, :ten)
  BASES =
    [new(1000, "M", nil, nil),
     new(100, "C", "D", "M"),
     new(10, "X", "L", "C"),
     new(1, "I", "V", "X")
    ]

  def self.convert(num)
    [].tap do |parts|
      BASES.inject(num) { |n, base|
        out, remainder = base.convert(n)
        parts << out if out
        remainder
      }
    end.join
  end

  def convert(num_raw)
    num, remainder = num_raw.divmod(base)
    return [nil, remainder] if num.zero?

    out =
      case
      when num == 0
        nil
      when num == 4
        "#{one}#{five}"
      when num == 5
        five
      when num == 9
        "#{one}#{ten}"
      when num <= 3
        one * num
      else
        five + one * (num - 5)
      end
    [out, remainder]
  end
end

class Fixnum
  def to_roman
    RomanBase.convert(self)
  end
end

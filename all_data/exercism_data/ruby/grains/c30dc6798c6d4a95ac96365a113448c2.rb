class Grains
  def square(number)
    case number
    when 1 then 1;
    else
      2**(number-1)
    end
  end

  def total
    (1..64).map {|sn| square(sn) }.reduce(&:+)
  end
end

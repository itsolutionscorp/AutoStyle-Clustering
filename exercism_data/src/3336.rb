class Hamming

  def compute(strand1, strand2)
    case
    when strand1.length != strand2.length then 0
    when strand1 = strand2 then 0
    else
      strand1.count("AGCT") - strand2.count("AGCT")
    end
  end

end

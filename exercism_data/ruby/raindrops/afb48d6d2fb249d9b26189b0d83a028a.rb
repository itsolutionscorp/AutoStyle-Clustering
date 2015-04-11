class Raindrops
  def convert(count)
    out = ""
    out << "Pling" if factor?(count, 3)
    out << "Plang" if factor?(count, 5)
    out << "Plong" if factor?(count, 7)

    out.empty? ? count.to_s : out
  end

  private

  def factor?(number, candidate)
    (number % candidate).zero?
  end
end

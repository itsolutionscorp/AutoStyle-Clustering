class Hamming
  def compute(*args, diff: 0)
    return diff if args.any?(&:empty?)
    a, b = args.map { |strand| strand[0] }
    remainder = args.map { |strand| strand[1..-1] }
    diff += (a == b) ? 0 : 1
    compute(*remainder, diff: diff)
  end
end

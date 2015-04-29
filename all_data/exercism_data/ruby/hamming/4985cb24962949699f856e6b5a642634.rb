class Hamming
  def self.compute(*args)
    raise ArgumentError if args.length != 2

    string1,string2 = args
    unmatched = 0

    until string1.length == 0 && string2.length == 0
      unmatched += 1 unless string1.slice!(0) == string2.slice!(0)
    end

    unmatched
  end
end

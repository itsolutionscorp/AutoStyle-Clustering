class ETL

  def self.transform(args)
    hash = {}
    args.each do |score, letters|
      letters.each do |l|
        hash[l.downcase] = score
      end
    end
    hash
  end

end

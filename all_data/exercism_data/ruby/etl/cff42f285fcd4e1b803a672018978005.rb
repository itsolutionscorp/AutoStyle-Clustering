class DB

  def initialize(db = {})
    @db = db
  end

  def add(key, val)
    keys = key.kind_of?(Array) ? key : [key]
    add_all keys, val
  end

  def to_hash
    @db
  end

  private

  def add_all(keys, val)
    keys.each do |key|
      @db[key.downcase] = val
    end
  end

end

class ETL

  def self.transform(old_db)
    new_db = DB.new

    old_db.each do |num_key, letters|
      new_db.add letters, num_key
    end

    new_db.to_hash
  end

  private


end

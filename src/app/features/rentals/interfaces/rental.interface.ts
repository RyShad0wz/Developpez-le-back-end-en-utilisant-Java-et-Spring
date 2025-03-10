export interface Rental {
	id: number;
	name: string;
	surface: number;
	price: number;
	picture: string;
	description: string;
	ownerId: number;         // Remplace owner_id par ownerId
	createdAt: Date;         // Remplace created_at par createdAt
	updatedAt: Date;         // Remplace updated_at par updatedAt
  }
  
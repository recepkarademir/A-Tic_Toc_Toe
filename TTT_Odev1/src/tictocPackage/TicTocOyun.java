/*
 *   Ekim  2018
 *   Recep KARADEMÝR   2015141003
 *   Tic-Tac-Toe (XOX) Oyununun Java programlama diliyle kodlanmasý
*/
package tictocPackage;


import java.util.Scanner;

public class TicTocOyun
{
	
	// global eriþilebilir deðiþkenler.
	public static char tahta[][] = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } }; // oyun tahtasýný harf dolduracaðýmýz dizi.
	public static int hamleSayisi = 1;// oyunda toplam 9 kez hamle yapýlabilir.
	public static int kazanma = 2; // kazanma == 3 ise hatalý konum tuþlamasý yapýlmýþ. kazanan == 1 ise bir oyuncu zafer kazandý. kazanan== 2 ise oyun devam ediyor
	public static int hamle=0; // oyuncunun seçtiði kutu numarasýný oyun tahtasýna yerleþtirmek için kullanýlan geçici konum tutucu.
	
	public static void main(String[] args)
	{
		
		System.out.print("Oyun tahtasýnda, harfinizi koymak istediðiniz\n");
		System.out.print("alanýn içindeki rakamý tuþlayýn.\n");
		System.out.println("\n\n\n");
		
		while(hamleSayisi <10 && kazanma == 2) // oyunda toplam 9 kez hamle yapýlabilir. Kazanan==2 deðilse oyun bitmiþtir.
		{
			tahtaYazdir(); // yazdýrma iþlemi
			tahtaKontrol(); // kurallara uygun konum kontrolü yapýlýyor ve harf tahtaya yerleþtiriliyor.
			
			if(kazanma==3) // harf yerleþtirmede problem varsa tekrar konum isteniyor.
			{
				while(true)// konumun hatalý veya önceden seçili konuma tekrar harf girilmemesi için yazýlan while döngüsü.
				{
					System.out.println("\n\n\n");
					tahtaYazdir(); // hatalý konum yerine, önceden seçilmemiþ ve [1..9] arasý  konum deðeri tekrar isteniyor.
					tahtaKontrol(); // kurallara uygun konum kontrolü yapýlýyor ve harf tahtaya yerleþtiriliyor.
					if(kazanma == 2) // kurallara uygun konum girildiyse oyun devam ediyor. Girilmediyse tekrar konum girilmesi isteniyor.
					{
						break; // konum doðru girildiyse while dan çýkýp, kazanan konrolüne geçilebilir.
					}
				}
			}
			
			// harf yerleþtirmede problem yoksa kazanan kontrolü yapýlmalý.
			kazananKontrol(); // kazanan varsa yazdýrma iþlemi farklý iþleyecek. oyunda kazananýn olup olmadýðý kontrolü yapýlýyor.
			if(kazanma == 2) // konum doðru girilmiþ, harf yerleþtirilmiþ ve kazanan kontrolü yapýlmýþ demek.Oyun devam edecekse bu if çalýþýr.
			{
				hamleSayisi++;// kazanan olsa da olmasa da hamle sayýsý artýrýlmalý çünkü bir hamle tamamlandý.
				System.out.println("\n\n\n");
			}
			else if(kazanma==1)// kazanan tespit fonksiyonu, kazanan olduðunu kazanma deðiþkenini 1 yaparak belirtirse bu koþul çalýþýr.
			{
				System.out.println("\n\n\n");
				tahtaYazdir(); // kazananý yazdýrma iþlemi
				break;
			}
		}
		
		if(hamleSayisi==10) // oyunda kazanan varsa burayý yazdýrmamak için hamle kontrolü yapýlýr. Burasý sadece beraberlik durumunda yazdýrýlýr.
		{
			kazanma=0; // bütün hamleler yapýldýysa, berabere bittiðini yazdýrmak için kazanma ==0 olmalý.
			tahtaYazdir(); // hamle sayýsý 9 olduysa beraberlik yazdýrma iþlemi
		}
	}
	
	
	static void tahtaYazdir() // kazanan true ise oyunu bitir
	{
		Scanner oku = new Scanner(System.in); // harf yerleþtirilecek konumu kullanýcýdan
		System.out.print("\n\tXOX Oyunu\t\n\n\n");
		System.out.print("1.Oyuncunun harf simgesi : X\n2.Oyuncunun harf simgesi : O\n\n");
		System.out.println("Seçtiðiniz rakamýn yerine kendi harf simgenizi koyarsýnýz.\n ");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[0][0] + "  |  " + tahta[0][1] + "  |  " + tahta[0][2] + "  \n");
		System.out.print("_____|_____|_____\n");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[1][0] + "  |  " + tahta[1][1] + "  |  " + tahta[1][2] + "  \n");
		System.out.print("_____|_____|_____\n");
		System.out.print("     |     |     \n");
		System.out.print("  " + tahta[2][0] + "  |  " + tahta[2][1] + "  |  " + tahta[2][2] + "  \n");
		System.out.print("     |     |     \n\n");
		
		if(kazanma == 2 || kazanma==3) // oyuna devam edilecek. Hatalý konum girildiyse veya oyun devam etmesi gerekiyorsa bu koþul çalýþýr.
		{
			if(hamleSayisi % 2 != 0) // hamleSayisi deðiþkeni 1 den baþlýyor. 1.Oyuncudan. Tek rakamlarda sýra 1. oyuncuda
			{
				System.out.println("Oyuncu 1, rakam giriniz :");
			}
			else // hamlesayýsý  çift rakam ise sýra ikinci oyuncudadýr.
			{
				System.out.println("Oyuncu 2, rakam giriniz :");
			}
			
			hamle = oku.nextShort();
			return ; // program main e dönmeli.
		}
		else if(kazanma == 1) // kazanan olursa 9 kez hamle beklemeye gerek yok.
		{
			if(hamleSayisi % 2 != 0)// hamleSayisi tek ise sýra  1. oyuncuda, çift ise 2.oyuncudadýr.
			{
				System.out.print("==> 1. oyuncu ( X ) oyunu kazandý");
			}
			else if(hamleSayisi % 2 == 0)// hamleSayisi tek ise sýra  1. oyuncuda, çift ise 2.oyuncudadýr.
			{
				System.out.print("==> 2. oyuncu ( O ) oyunu kazandý");
			}
			return ;// program main e dönmeli.
		}
		else if(kazanma == 0) // 0 kodu beraberlik kodudur.
		{
			System.out.println("Oyun berabere bitti ! ");
			return ;// program main e dönmeli.
		}
	}
	
	
	static void tahtaKontrol()
	{

		if(hamle > 9 || hamle < 1) // konum deðeri oyun tahtasýndaki deðiþkenlerden biri olmalý.
		{
			System.out.print("\n\nSeçtiðiniz yer oyun alaný dýþýnda !");
			kazanma =3 ;//hatalý tahta konumu seçildiðinde kazanma 3 deðerini alacak.
		}
		else
		{
			char harf;
			// oyuncu sýrasýna bakarak hangi harfin yerleþtirilmesi gerektiði tespit ediliyor.
			if(hamleSayisi % 2 != 0)
			{
				harf = 'X';
			}
			else
			{
				harf = 'O';
			}
			// Harf yerleþtirilmesi yapýlmadan önce, yerleþim yapýlacak yerin boþ olup olmadýðý kontrol edilir. X ve O olmayacak.
			switch(hamle) // oyun tahtasýndaki seçili yere, þuanki harf yerleþtiren oyuncunun harfi yerleþtirilir.
			{
				case 1:
					if(tahta[0][0] =='1') // burasý 1 ise yerleþtirme yapýlabilir.
					{
						tahta[0][0] = harf;kazanma=2; // doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 2:	
					if(tahta[0][1] =='2')
					{
						tahta[0][1] = harf;kazanma=2; // doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 3:
					if(tahta[0][2] =='3')
					{
						tahta[0][2] = harf;kazanma=2; // doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 4:
					if(tahta[1][0] =='4')
					{
						tahta[1][0] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 5:
					if(tahta[1][1] =='5')
					{
						tahta[1][1] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 6:
					if(tahta[1][2] =='6')
					{
						tahta[1][2] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 7:
					if(tahta[2][0] =='7')
					{
						tahta[2][0] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 8:
					if(tahta[2][1] =='8')
					{
						tahta[2][1] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
				case 9:
					if(tahta[2][2] =='9')
					{
						tahta[2][2] = harf;kazanma=2;// doðru bir konum deðeri girildiyse harf yerleþtirme baþarýlý olduðunu bildiren 2 deðeri geri gidecek.
					}
					else
					{
						System.out.print("\n\nSeçtiðiniz yere önceden harf yerleþtirilmiþ !");
						kazanma =3;
					}
					break;
			}
		}	
	}
	
	
	static void kazananKontrol()// kazanan varsa, kazananýn olduðunu bildiren deðiþimi yapýyor.
	{
		// eðer alanlardaki 3 lü harfler aynýysa, kazanma 1 deðerine sahip olacak.
		
		// çarpraz eþitlik için kontrol				
		if(     tahta[0][0] == tahta[1][1] && tahta[1][1] == tahta[2][2]     ) // 1 nuralý yer == 5 numaralý yer == 9 numaralý yer.
		{
			kazanma = 1; // 1numaralý bölge 5 numaralý bölge ve 9 numaralý bölge ayný harfse oyunu bir oyuncu kazandý.
		}
		else if(tahta[0][2] == tahta[1][1] && tahta[1][1] == tahta[2][0])
		{
			kazanma = 1;
		}
		// dikey eþitlik için kontrol
		else if(tahta[0][0] == tahta[1][0] && tahta[1][0] == tahta[2][0])
		{
			kazanma = 1;
		}
		else if(tahta[0][1] == tahta[1][1] && tahta[1][1] == tahta[2][1])
		{
			kazanma = 1;
		}
		else if(tahta[0][2] == tahta[1][2] && tahta[1][2] == tahta[2][2])
		{
			kazanma = 1;
		}
		//yatay eþitlik için kontrol
		else if(tahta[0][0] == tahta[0][1] && tahta[0][1] == tahta[0][2])
		{
			kazanma = 1;
		}
		else if(tahta[1][0] == tahta[1][1] && tahta[1][1] == tahta[1][2])
		{
			kazanma = 1;
		}
		else if(tahta[2][0] == tahta[2][1] && tahta[2][1] == tahta[2][2])
		{
			kazanma = 1;
		}
		// eþitlik olmazsa oyun devam edecek.
		else
		{
			kazanma = 2; // kazanan yoksa devam anlamýnda 2 kodunu gönderecek.
		}
	}
}